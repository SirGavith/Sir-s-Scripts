// /farm command by Sir_Gav  v2.1
//written origionally for for Terrene SMP

//      ~~COMMANDS~~

//  To get this knowledge but in game:
//  /farm   or   /farm help

//  To create a new farm:
//  /farm add [farmname] [Xcoord] [Ycoord] [Zcoord] [dimension]
//  farmname is the name, dimensions can be 'overworld', 'nether', or 'end'
 
//  For finding out what farms are saved:
//  /farm list
 
//  For starting a farm:
//  /farm start [farmname]
//  Valid names can be found using /farm list
 
//  For stopping a farm:
//  /farm stop [farmname]
//  Note that the fake player's name is the same as the farmname

__config()-> ({'stay_loaded' -> true});

__tellraw(json) -> (run('tellraw '+player()+' '+json));

__tellPlayer(text) -> (__tellraw('"'+text+'"'));

__command() -> (help());

__spawnPlayer(p, posX, posY, posZ,dim)->(
    run('execute in '+dim+' run player '+p+' spawn at '+posX +' '+ posY+' '+posZ);
    if(player(p) ~ 'gamemode' != 'survival',modify(player(p),'gamemode','survival'));
);

__killPlayer(p) -> (run('player '+p+' kill'));

__listFarms() -> (
    savedFile = read_file('savedFarms', 'raw');
    if(savedFile != null,
        farmList = [];
        for(read_file('savedFarms', 'raw'),
            nextFarm = split(',',_):0;
            farmList += nextFarm;
        );
        return(farmList),
        return(false);
    );
);

add(name,posX,posY,posZ,dimension) -> (
    isValid = true;
    if(__listFarms() != false,  //Check for duplicate names
        for(__listFarms(),
            if(_==name,
                __tellPlayer('There\'s already a farm with that name! Use /farm list to find out what farms are saved.');
                isValid = false;
                break();
            );
        );
    );
    if(type(posX) != 'number' || type(posY) != 'number' || type(posZ) != 'number',  //Check for valid coords
        __tellPlayer('One or more of your coords is not a number.');
        isValid = false;
    );
    if(dimension == 'overworld',dim = 'overworld',  //Check for valid dimensions
        dimension == 'nether',dim = 'the_nether',
        dimension == 'end',dim = 'the_end',
        __tellPlayer(dimension+' is not a valid dimension. Valid dimensions are `overworld`,`nether`, and `end`.');
        isValid = false;
    );

    if(isValid == true, //if all args seem right
        write_file('savedFarms', 'raw', name+','+posX+','+posY+','+posZ+','+dim+'\n');
        __tellPlayer('Successfully added '+name);
    );
);

help() -> (
    __tellraw('["",{"text":"Help for the /farm command:","bold":true}]');
    __tellPlayer('Click on these links to get info about each subcommand.');
    __tellraw('["",{"text":"[/farm add]","color":"green","underlined":true,"clickEvent":{"action":"run_command","value":"/tellraw @p {\\"text\\":\\"To create a new farm: - /farm add  [farmname] [Xcoord] [Ycoord] [Zcoord] [dimension] - Dimensions can be `overworld`, `nether`, or `end`\\"}"},"hoverEvent":{"action":"show_text","value":{"text":"","extra":[{"text":"[/farm add - help]","color":"green"}]}}},{"text":"/","color":"dark_gray"},{"text":"[/farm list]","color":"green","underlined":true,"clickEvent":{"action":"run_command","value":"/tellraw @p {\\"text\\":\\"For finding out what farms are saved: - /farm list\\"}"},"hoverEvent":{"action":"show_text","value":{"text":"","extra":[{"text":"[/farm list - help]","color":"green"}]}}},{"text":"/","color":"dark_gray","bold":"false"},{"text":"[/farm start]","color":"green","underlined":true,"clickEvent":{"action":"run_command","value":"/tellraw @p {\\"text\\":\\"For starting a farm: - /farm start [farmname] - Valid names can be found using /farm list\\"}"},"hoverEvent":{"action":"show_text","value":{"text":"","extra":[{"text":"[/farm start - help]","color":"green"}]}}},{"text":"/","color":"dark_gray","bold":"false"},{"text":"[/farm stop]","color":"green","underlined":true,"clickEvent":{"action":"run_command","value":"/tellraw @p {\\"text\\":\\"For stopping a farm: - /farm stop [farmname] - Note that the fake player\'s name is the same as the farmname\\"}"},"hoverEvent":{"action":"show_text","value":{"text":"","extra":[{"text":"[/farm stop - help]","color":"green"}]}}}]');
    __tellraw('["",{"text":"Version 2.1","italic":true,"color":"gray"}]')
);

list() -> (
    if(__listFarms() != false,
        __tellPlayer('Farm Options: '+join(', ',__listFarms())),    //list known farms
        __tellPlayer('No farms are saved yet. Try /farm add to add a new farm') //else
    );
);

start(farmName) ->(
    farmExists = false;
    savefile = read_file('savedFarms', 'raw');
    for(savefile,   // for each saved farm
        farmList = split(',',_);    //split it into a list
        if(farmList:0 == farmName,  //if the name is right
            __spawnPlayer(farmList:0,farmList:1,farmList:2,farmList:3,farmList:4); //spawn the player
            farmExists = true;
            break();
        );
    );
    if(farmExists == false,
        __tellPlayer('That farm doesn\'t exist! Try /farm list to find out what is saved');
    );
);

stop(farmName) -> (
    __killPlayer(farmName) //simple enough, kills player
);