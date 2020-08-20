__config()-> {'stay_loaded' -> true,'scope'->'global'};

//change this value to adjust the radius that blocks start moving toward you
global_pickup_radius = 7;

__on_tick()->(
    magnet_slot = inventory_find(player(), 'nether_star');
    if(magnet_slot != null,
        has_magnet = true;
        magnet_info = inventory_get(player(),magnet_slot);
        //magnet_info:2 is the item NBT data
        if( magnet_info:2 != null, //if item has NBT (a name)
            item_name = split('"',split('text":"',split('Name:\'',magnet_info:2):1):1):0; //gets the name from the json
            if(lower(item_name) == 'magnet', _magnetize());
        );
    );    
);

_magnetize() -> (
    nearby_items = entity_selector('@e[type=item]');
    for(nearby_items,
        item = _;
        is_near = [false,false,false]; //per axis
        for(pos(item)-pos(player()),
            if(abs(_)<global_pickup_radius,is_near:_i = true);
        );
        if(is_near:0 == true && is_near:1 == true && is_near:2 == true,
            vector = pos(player())-pos(item);
            for(vector, vector:_i = _/70);

            modify(item, 'accelerate', vector);
        )
    )
)