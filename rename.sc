//rename by Sir_Gav

__command() -> (null);

hand(name) ->
(
    p = player();
    s = query(p, 'selected_slot');
    holds = query(p, 'holds');
    print(get(holds,2))
    //inventory_set(p,s,get(holds,1),get(holds,0),'{display:{Name:{"text":"'+name+'"}}}');
        
)