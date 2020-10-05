import('mccmds','_display_title');

_config() -> {'scope'->'global'};

global_common_shorthands = {'0'->'air','wood'->'oak_planks','diamond'->'diamond_block','grass'->'grass_block','grass_bush'->'grass','gold'->'gold_block','iron'->'iron_block'};

global_pos1 =null;
global_pos2 =null;
global_render_mode = 'box';

global_tool = 'wooden_sword';

__command() -> (
    p = player();
    print('');
    print(p,format('  ','od 1','lb  WorldEdit Help ','od 1'));
    print(p,format('w The tool is a ','wbu '+global_tool,'^w Give you a '+global_tool,'!/give @p '+global_tool));
    print(p,format('w To select a region, ','b left click on pos1','w  and ','b right click on pos2','w  with the tool'));
    print(p,'');
    print(p,format('  ','od 1','lb  Commands ','od 1'));
    print(p,format('w Click ','cb here','^ Show WorldEdit command help','!/script in we invoke _help_command','w  to show WorldEdit command help'));
    print(p,'');
    print(p,format('  ','od 1','lb  Settings ','od 1'));
    print(p,format('w Click ','cb here','^ Show WorldEdit settings','!/script in we invoke _settings_command','w  to show WorldEdit settings'));
    print(p,format('gi WorldEdit in scarpet by Sir_Gavith v1.0'));
    ''
);

_help_command()-> (
    p = player();
    print(p,'');
    print(p,format('  ','od 1','lb  WorldEdit Command Help ','od 1'));
    print(p,format('c /we fill <block> ','w Fills your selection with the specified block'));
    print(p,format('c /we x ','w  Cuts the current selection and places it in your clipboard'));
    print(p,format('c /we c ','w  Copies the current selection to your clipboard'));
    print(p,format('c /we v ','w  Pastes the clipboard where you are'));
    print(p,format('c /we tool ','^w Execute /we tool','!/give @p '+global_tool,'w  Gives you the selection tool'));
);

_settings_command() -> (
    p = player();
    print(p,'');
    print(p,format('  ','od 1','lb  WorldEdit Settings ','od 1'));
    print(p,format('c /we settings tool <tool>','^ Prompt Command','?/we settings tool <tool>','w  Changes the selection tool to [tool]. Swords work best.'));
    ''
);


__on_player_clicks_block(player, block, face) -> ( //pos1 trigger
    item_tuple = inventory_get(player, player~'selected_slot');    
    
    if(
        item_tuple:0 == global_tool,
        global_pos1 = pos(block);
        _display_title(player,'actionbar','Pos1 set to '+global_pos1);
        _draw_region(player)
    );
);

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> ( //pos2 trigger
    if(
        item_tuple:0 == global_tool && hand == 'mainhand',
        global_pos2 = pos(block);
        _display_title(player,'actionbar','Pos2 set to '+global_pos2);
        _draw_region(player)
    );
);


x() -> (
    vol = _save_area('cut');
    _display_title(player(),'actionbar','Cut '+vol+' blocks');
    ''
);

c() -> ( //copy
    vol = _save_area('copy');
    _display_title(player(),'actionbar','Copied '+vol+' blocks');
    ''
);

v() -> ( //paste
    clipboard = read_file('clipboard','text');
    if(!clipboard,return('There\'s nothing saved on the clipboard'));

    //deal with metadata
    paste_name = clipboard:0;
    paste_size = parse_nbt(clipboard:1);
    player_relative_pos = values(parse_nbt(clipboard:2));
    loop(3,delete(clipboard,0)); //delete metadata

    pos1 = player_relative_pos+pos(player());
    for(pos1,pos1:_i = round(pos1:_i)); //round pos1
    pos2 = pos1 + paste_size;

    i = 0;
    volume(pos1,pos2,
        block = parse_nbt(clipboard:i);
        props = [];
        for(block:'block_properties',
            props += _;
        );

        if(block:'block_data' != null,
            set(pos(_),block:'name',props,encode_nbt(block:'block_data')),
            set(pos(_),block:'name',props);
        );
        i += 1;
    );
    _display_title(player(),'actionbar','Pasted '+i+' blocks');
    ''
);

fill(block) -> (
    block_num = volume(global_pos1,global_pos2,_we_set([_x,_y,_z],block));  
    _display_title(player(),'actionbar','Filled '+block_num+' blocks');
    '';
);

tool() -> (
    run('give '+player()+' '+global_tool+' 1');
);

sphere(diameter,block,is_hollow) -> (
    if(is_hollow, mode = 'hollow', mode = 'solid');


    return(null);
);

render_shape(shape,size) -> (
    center = (global_pos1-global_pos2)/2+global_pos2;
    if(
        shape == 'sphere', shape = _find_shape_blocks('sphere','solid',size,center,center),
        shape == 'ellipse', shape = _find_shape_blocks('ellipse','solid',size,global_pos1,global_pos2),
        _display_title(player(),'actionbar','That shape is unsupported at the moment');
    );

    
    for(shape,
        if(!shape~(_+[0,1,0]), _draw_face(_,(player()),'top'));
        if(!shape~(_+[1,0,0]), _draw_face(_,player(),'east'));
        if(!shape~(_+[0,0,1]), _draw_face(_,player(),'south'));
        if(!shape~(_+[-1,0,0]), _draw_face(_,player(),'west'));
        if(!shape~(_+[0,0,-1]), _draw_face(_,player(),'north'));
        if(!shape~(_+[0,-1,0]), _draw_face(_,player(),'bottom'));
        
    );

);

_find_shape_blocks(shape,mode,size,focus1,focus2) -> (
    center_to_focus = (focus1-focus2)/2;
    center = center_to_focus+focus2;

    shape_blocks = [];
    scan(center,center_to_focus+size,
        dist_focus1 = sqrt((focus1:0-_x)^2+(focus1:1-_y)^2+(focus1:2-_z)^2);
        dist_focus2 = sqrt((focus2:0-_x)^2+(focus2:1-_y)^2+(focus2:2-_z)^2);
        dist_center = sqrt((center:0-_x)^2+(center:1-_y)^2+(center:2-_z)^2);
        total_dist = dist_focus1+dist_focus2;

        if(shape == 'sphere' || shape == 'ellipse',

            if(mode == 'hollow',
                if(total_dist >= size - 1 && total_dist <= size + 1,
                    shape_blocks += pos(_);
                ),
                mode == 'solid',
                if(total_dist <= size + 1,
                    shape_blocks += pos(_);
                );
            );
        );
    );
    return(shape_blocks);
);


settings(setting,value) -> (
    if(setting == 'tool', global_tool = value; _display_title(player(),'actionbar','Changed Tool to '+global_tool););
    ''
);

_draw_region(player) -> (
    //cant render w/o both points
    if(global_pos1 != null && global_pos2 != null,

        //add 1 to the furthest positive pos in each direction so the box covers the whole selection
        //this hurts my brain
        renderer_pos1 = [];
        renderer_pos2 = [];
        for(range(3),
            if(global_pos1:_ >= global_pos2:_,
            renderer_pos1:_ = global_pos1:_+1.01;
            renderer_pos2:_ = global_pos2:_-0.01,
            renderer_pos2:_ = global_pos2:_+1.01;
            renderer_pos1:_ = global_pos1:_-0.01;
            );
        );
        center = (renderer_pos1-renderer_pos2)/2+renderer_pos2;

        draw_shape('box',100,{'from' -> renderer_pos1,'to' -> renderer_pos2,'line' -> 6,'fill' -> 0xC0C0C070,'player' -> player});
        draw_shape('sphere',100,{'center' -> center,'radius' -> 1/16,'level'->16,'color'->0x3000FFFF,'fill'->0x3000FFFF,'player'->player});
    );
);

_draw_face(block_pos,player,face) -> (

    //print('Currently drawing '+block_pos+' face: '+face);


    if(
        face == 'top',    pos1_offset = [-0.001, 1.001,-0.001]; pos2_offset = [ 1.001, 1.001, 1.001],
        face == 'north',  pos1_offset = [-0.001,-0.001,-0.001]; pos2_offset = [ 1.001, 1.001,-0.001],
        face == 'west',   pos1_offset = [-0.001,-0.001,-0.001]; pos2_offset = [-0.001, 1.001, 1.001],
        face == 'south',  pos1_offset = [-0.001,-0.001, 1.001]; pos2_offset = [ 1.001, 1.001, 1.001],
        face == 'east',   pos1_offset = [ 1.001,-0.001,-0.001]; pos2_offset = [ 1.001, 1.001, 1.001],
        face == 'bottom', pos1_offset = [-0.001,-0.001,-0.001]; pos2_offset = [ 1.001,-0.001, 1.001];
    );
    draw_shape('box',100,{'from' -> block_pos + pos1_offset,'to' -> block_pos + pos2_offset ,'line' -> 3,'fill' -> 0xC0C0C070,'player' -> player})
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
    ''
);

_save_area(mode) -> ( //mode can be 'cut' to remove blocks or anything else to not
    if(!global_pos1 || !global_pos2, return('You need two points selected to copy'));
    delete_file('clipboard','text');

    player_relative_pos = {};
    for(global_pos1-pos(player()),
        coord = ['x','y','z']:_i;
        player_relative_pos:coord = _;
    );

    write_file('clipboard','text','clipboard');
    write_file('clipboard','text',str(global_pos2-global_pos1));
    write_file('clipboard','text',str(player_relative_pos));
    vol = 0;
    volume(global_pos1, global_pos2,
        b = block(_);
        props = {};
        for(block_properties(b),
            props:_ = property(b,_);
        );
        block_data = block_data(b);
        if(block_data,
            write_file('clipboard','text',encode_nbt({'name'->str(b),'block_properties'->props,'block_data'->block_data})),
            write_file('clipboard','text',encode_nbt({'name'->str(b),'block_properties'->props}));
        );
        if(mode=='cut',set(_,'air'));


        vol += 1;
    );
    return(vol);
);