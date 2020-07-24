//speed by Sir_Gav

__command() -> (null);
  //

effect(num) ->
(
    modify(player(), 'effect', 'speed', 0, 0);
    if
    (
        num == 0,
        _clearspeed,
        //else
        modify(player(), 'effect', 'speed', 999999, num-1, true, true);
    )
    
);

_clearspeed() ->
(
    modify(player(), 'effect', 'speed', 0, 0);
)