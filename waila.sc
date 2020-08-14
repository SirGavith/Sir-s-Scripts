import(mccmds,bossbar_add,bossbar_set_players,bossbar_set_name,bossbar_remove,bossbar_set_color,bossbar_set_visible);

//waila - what am i looking at by Sir_Gav
__config()-> {'stay_loaded' -> true,'scope'->'global'};

bossbar_add('waila','');
bossbar_set_players('waila',player());
bossbar_set_color('waila','white');
bossbar_visible = true;

set_bossbar(facing_block) -> (
    if(
        facing_block == 'air',
        bossbar_set_visible('waila','false');
        bossbar_visible = false,
        //else
        bossbar_set_name('waila',format_block_name(facing_block));
        bossbar_set_visible('waila','true');
        bossbar_visible = true;
    );
);

format_block_name(mc_name) -> (
    //fancy_name = '';
    for(split('_',mc_name),
        fancy_name += title(_)+' ';
    );
    return(fancy_name);
);

player_looking_at(target) -> (
    location = pos(target)+[0,1.6,0];
    vector = target~'look';
    distance = 0;
    facing_block = 'air';
    while(facing_block == 'air', 24,
        facing_block = block(location+distance*vector);
        distance += 0.2;
    );
    return(facing_block);
);

__on_tick() -> (
    facing_block = player_looking_at(player());
    if(
        facing_block == 'air' && bossbar_visible == true || facing_block != 'air' &&bossbar_visible == false,
        set_bossbar(facing_block);
    );
);

__on_close() -> (bossbar_remove('waila'))