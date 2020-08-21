global_tillable_blocks = ['dirt','coarse_dirt','grass_block','mycelium','grass_path'];

__config()-> ({'stay_loaded' -> true});

__command() ->(
    block = player() ~ 'trace';
    if(
        global_tillable_blocks ~ block != null,
        set(pos(block),'farmland'); '',
        _display_title(player(),'actionbar','That\'s not a tillable block!'); '';
    );
);

_display_title(target,location,title) -> run('title '+target+' '+location+' "'+title+'"')