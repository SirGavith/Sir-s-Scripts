__on_player_breaks_block(player, block) -> (
    schedule(0,'broken_carefully',player,block);
);

broken_carefully(player, block) -> (
    item = entity_area('item',pos(block):0+0.5,pos(block):1+0.5,pos(block):2+0.5, 0.4, 0.3, 0.4):0;
    modify(item,'pos',pos(player));
    modify(item, 'motion',0,0,0);
    modify(item, 'pickup_delay', 0);
)