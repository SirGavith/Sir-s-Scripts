//right click harvest for crops by Sir_gav

//to add support for other crops, add to this map with the name and the max age
global_crops = {'wheat' -> 7, 'carrots' -> 7, 'potatoes' -> 7, 'beetroots' -> 3, 'nether_wart' -> 3, 'cocoa' -> 2};
global_tall_crops = {'sugar_cane' -> 'up', 'cactus' -> 'up', 'bamboo' -> 'up', 'twisting_vines_plant' -> 'up', 'kelp_plant'-> 'up', 'weeping_vines_plant'->'down', 'vine'-> 'down'};

__config()-> {'stay_loaded' -> true};

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec)-> (
    if(hand == 'mainhand', // both mands triggering breaks this thing for tall crops
        b_name = str(block);
        //make b_name consistent for dumb blocks that have different names (im looking at you 1.16 vines and kelp)
        if(
            b_name == 'twisting_vines', b_name = 'twisting_vines_plant',
            b_name == 'weeping_vines',  b_name = 'weeping_vines_plant',
            b_name == 'kelp', b_name = 'kelp_plant';
        );
        if(
            //standard crops
            has(global_crops,b_name),
            age = property(pos(block),'age');
            if(
                age == global_crops:b_name,
                b_pos = pos(block);
                harvest(player,b_pos);
                set(b_pos,block,'age',0) ;

            ),
        //tall crops
            has(global_tall_crops,b_name),
            b_pos = pos(block);
            if(
                global_tall_crops:b_name == 'up',
                //crop grows up
                while(block(b_pos + [0,-1,0]) == b_name, 256, //find bottommost crop
                    b_pos = b_pos + [0,-1,0]);
                harvest(player,b_pos+[0,1,0]),

                global_tall_crops:b_name == 'down',
                //else if it grows down
                while(block(b_pos + [0,1,0]) == b_name, 256,
                    b_pos = b_pos + [0,1,0]);
                harvest(player,b_pos+[0,-1,0])
            )
        )
    )
)