//stat by Sir_Gav
__command() ->
(
    run('tellraw @p "Try saying /stat show [yourstat] or /stat hide to hide all stats"');
);


show(stat)->
(
    scoreboard_add(stat,'level');
    scoreboard_display('sidebar', stat) 
)


