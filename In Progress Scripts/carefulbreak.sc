//Sir_Gav tries to recreate carefulbreak

__config()-> ({'stay_loaded' -> true});

_killitem(posX,posY,posZ) ->(
    print('execute positioned '+posX+' '+posY+' '+posZ+' run kill @e[limit=1,type=item]');
);

_brokencarefully(p,b) ->
(
    coords = pos(b);
    print(nbt(b));
    print('Broken Carefully');
    posX = get(coords,0)+0.5;
    posY = get(coords,1)+0.5;
    posZ = get(coords,2)+0.5;
    _killitem(posX,posY,posZ);
    run('give '+p+' '+b);
);

__on_player_breaks_block(player, block) -> (
    if(player ~ 'sneaking' == true, _brokencarefully(player,block));
)