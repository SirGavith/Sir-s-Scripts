//vanish by Sir_Gav


__command() ->
(
    if
    (   query(player(), 'effect', 'invisibility') == null,
        _hide_player(player()),
        //else
        _show_player(player());
    )
);


_hide_player(p)->
(
    print('Hiding Player');
    modify(p, 'effect','invisibility', 999999, 1, true, true);
    modify(p, 'effect','speed', 999999, 0, true, true);
    modify(p, 'effect','night_vision', 999999, 1, true, true);
);

_show_player(p) ->
(
    print('Showing Player');
    modify(p, 'effect','invisibility');
    modify(p, 'effect','speed');
    modify(p, 'effect','night_vision');
);