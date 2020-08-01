//these first three methods will eventually be avalible in mccmds
bossbar_add(id,name) -> run('bossbar add '+id+' "'+name+'"');
bossbar_set_players(id,target) -> run('bossbar set '+id+' players '+target);
bossbar_set_name(id,name) -> run('bossbar set '+id+' name "'+name+'"');
bossbar_remove(id) -> run('bossbar remove '+id);  
bossbar_set_color(id,color) -> run('bossbar set '+id+' color '+color);

//waila - what am i looking at by Sir_Gav
__config()-> {'stay_loaded' -> true,'scope'->'global'};


//todo change color and fullness
bossbar_add('waila','');
bossbar_set_players('waila',player());
bossbar_set_color('waila','white');
bossbar_visible = true;



hide_bossbar() -> (
    run('bossbar set waila visible false');
    bossbar_visible = false;
);
show_bossbar() -> (
    run('bossbar set waila visible true');
    bossbar_visible = true;
);
correct_bossbar(facing_block) -> (
    if(
        facing_block == 'air', hide_bossbar(),
        //elif
        facing_block != 'air', show_bossbar();
    );
);

format_block_name(mc_name) -> (
    fancy_name = '';
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
    correct_bossbar(facing_block);
    bossbar_set_name('waila',format_block_name(facing_block));
);

__on_close() -> (bossbar_remove('waila'))


//__command() ->