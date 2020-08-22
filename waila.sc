//waila - what am i looking at by Sir_Gav
//requires mccmds, a script library I've developed

import('mccmds','bossbar_add','bossbar_set_players','bossbar_set_name','bossbar_remove','bossbar_set_color','bossbar_set_visible');

__config()-> {'stay_loaded' -> true,'scope'->'global'};

//init
bossbar_add('waila','');
bossbar_set_players('waila',player());
bossbar_set_color('waila','white');
global_bossbar_visible = true;

__on_tick() -> (
    facing_block = player()~'trace';
    fancy_name = '';
    for(split('_',facing_block), fancy_name += title(_)+' ');
    bossbar_set_name('waila',fancy_name);
    
    if(
        facing_block == null && global_bossbar_visible == true, bossbar_set_visible('waila','false'); global_bossbar_visible = false,
        facing_block != null && global_bossbar_visible == false, bossbar_set_visible('waila','true'); global_bossbar_visible = true;
    );
);

__on_close() -> (bossbar_remove('waila'))