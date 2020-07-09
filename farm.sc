// /farm command for Terrene SMP by Sir_Gav v1.0

__config()-> ({l('stay_loaded', true)});

__command()->
(
    run('tellraw '+player()+' "Try /farm witchstart or /farm ironstop"')
);

__spawnplayer(who, whereX, whereY, whereZ)->(
    run('player '+who+' spawn at ' + whereX + ' ' + whereY + ' ' + whereZ);
    if( player(who) ~ 'gamemode' != 'survival',modify(player(who),'gamemode','survival'); )
);

__killplayer(who) -> (run('player '+who+' kill'));


witchstart() -> (__spawnplayer('witch', -764.5, 183, -203.5));
witchstop() ->  (__killplayer('witch'); );

ironstart() ->  (__spawnplayer('iron',-472.5, 200, -230.5));
ironstop() ->   (__killplayer('iron'))
