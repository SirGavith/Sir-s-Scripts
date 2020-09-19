import('mccmds','display_title');

global_common_shorthands = {'0'->'air','wood'->'oak_planks','diamond'->'diamond_block','grass'->'grass_block','grass_bush'->'grass','gold'->'gold_block','iron'->'iron_block'};

global_pos1 =null;
global_pos2 =null;

__command() -> (null);


//pos1 trigger
__on_player_clicks_block(player, block, face) -> (
    item_tuple = inventory_get(player, player~'selected_slot');    
    if(
        item_tuple:0 == 'wooden_sword',
        global_pos1 = pos(block);
        display_title(player,'actionbar','Pos1 set to '+global_pos1);
        draw_region(player)
    );
);

//pos2 trigger
__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(
        item_tuple:0 == 'wooden_sword' && hand == 'mainhand',
        global_pos2 = pos(block);
        display_title(player,'actionbar','Pos2 set to '+global_pos2);
        draw_region(player)
    );
);

draw_region(player) -> (
    //cant render w/o both points
    if(global_pos1 != null && global_pos2 != null,

        //add 1 to the furthest positive pos in each direction so the box covers the whole selection
        //this hurts my brain
        renderer_pos1 = [null,null,null];
        renderer_pos2 = [null,null,null];
        for(range(3),
            if(global_pos1:_ >= global_pos2:_,
            renderer_pos1:_ = global_pos1:_+1.01;
            renderer_pos2:_ = global_pos2:_-0.01,
            renderer_pos2:_ = global_pos2:_+1.01;
            renderer_pos1:_ = global_pos1:_-0.01;
            );
        );
        draw_shape('box',25,{'from' -> renderer_pos1,'to' -> renderer_pos2,'line' -> 6,'fill' -> 0xC0C0C070,'player' -> player});
    );
);

_we_set(pos,block) -> (
    if( //if block is a shorthand then apply it
        has(global_common_shorthands,str(block)),
        block = global_common_shorthands:str(block);
    );
    without_updates(
        //this shows up in error messages when its not a valid block
        //THATS NOT A VALID BLOCK
        set(pos,block)
        //THATS NOT A VALID BLOCK
    );
);


fill(block) -> (
    block_num = volume(global_pos1,global_pos2,_we_set([_x,_y,_z],block));  
    display_title(player(),'actionbar','Filled '+block_num+' blocks');
    '';
);

copee() -> (
    if(!global_pos1 || !global_pos2, return('You need two points selected to copy'));
    delete_file('clipboard','text');
    player_relative_pos = [0,0,0];
    for(global_pos1-pos(player()), player_relative_pos:_i = round(_));
    //write metadata
    write_file('clipboard','text','Clipboard');
    write_file('clipboard','text',str(global_pos2-global_pos1));
    write_file('clipboard','text',str(player_relative_pos));
    volume(global_pos1, global_pos2,
        block = block(_);
        props = {};
        for(block_properties(block),
            if(property(block,_) == 'false',
                props:_ = 'ffalse',
                property(block,_) == 'true',
                props:_ = 'ttrue', //parse_nbt would otherwise turn this into 0, which I do not want
                props:_ = property(block,_);
            );
        );
        write_file('clipboard','text',{'name'->block,'block_properties'->props,'block_data'->block_data(block)});   
    );
);




paste() -> (
    clipboard = read_file('clipboard','text');
    if(!clipboard,return('There\'s nothing saved on the clipboard'));

    //deal with metadata
    paste_name = clipboard:0;
    paste_size = parse_nbt(clipboard:1);
    player_relative_pos = parse_nbt(clipboard:2);
    loop(3,delete(clipboard,0));
    
    pos1 = [0,0,0];
    for(pos(player()), pos1:_i = round(_+player_relative_pos:_i));
    pos2 = pos1 + paste_size;

    i = 0;
    volume(pos1,pos2,
        block = parse_nbt(clipboard:i);
        props = [];
        for(
            block:'block_properties',
            props += _;
            if(block:'block_properties':_ == 'ffalse',
                props += 'false',
                block:'block_properties':_ == 'ttrue',
                props += 'true',
                props += block:'block_properties':_;
            );
        );

        //set(pos(_),block:'name',props);
        if(block:'block_data' != 'null',
            set(pos(_),block:'name',props,encode_nbt(block:'block_data')),
            set(pos(_),block:'name',props);
        );
        i += 1;
    );
);


wand() -> (
    run('give '+player()+' wooden_sword 1');
)