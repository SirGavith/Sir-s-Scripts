//MC Commands lib by Sir_Gav
//go to https://minecraft.gamepedia.com/Commands for a great list of commands + args

//you can use built in scarpet things instead of /attribute

advancement_grant_everything(target) -> run('advancement grant '+target+' everything');
advancement_grant_specific(target,advancement) -> run('advancement grant '+target+' only '+advancement);
advancement_grant_special(target,keyword,advancement) -> run('advancement grant '+target+' '+keyword+' '+advancement);
//keyword must be: from|through|until
advancement_revoke_everything(target) -> run('advancement revoke '+target+' everything');
advancement_revoke_specific(target,advancement) -> run('advancement revoke '+target+' only '+advancement);
advancement_revoke_special(target,keyword,advancement) -> run('advancement revoke '+target+' '+keyword+' '+advancement);
//keyword must be: from|through|until

ban(target,reason) -> (run('ban '+target+' '+reason));
banip(target,reason) -> (run('ban-ip '+target+' '+reason));
// banlist(target) -> ();
//This just prints in chat

bossbar_add(id,name) -> (run('bossbar add '+id+' "'+name+'"'));
// bossbarGetMax(id,)
// bossbarGettargets(id,)
// bossbarGetValue(id,)
// bossbarGetVisible(id,)
// bossbarList(target) -> ();
//These just print in chat
bossbar_remove(id) -> run('bossbar remove '+id);      
bossbar_set_color(id,color) -> run('bossbar set '+id+' color '+color);
bossbar_set_max(id,max) -> run('bossbar set '+id+' max '+max);
bossbar_set_name(id,name) -> run('bossbar set '+id+' name "'+name+'"');
bossbar_set_players(id,target) -> run('bossbar set '+id+' players '+target);
bossbar_set_style(id,style) -> run('bossbar set '+id+' style '+style);
//style can be: progress|notched_6|notched_10|notched_12|notched_20
bossbar_set_value(id,value) -> run('bossbar set '+id+' value '+value);
bossbar_set_visible(id,visibility) -> run('bossbar set '+id+' visible '+visibility);


clear_specific(target,item,maxCount) -> run('clear '+target+' '+maxCount);
clear(target) -> (run('clear '+target));

//theres better ways instead of /clone

//theres better ways instead of /data

datapack_disable(name) -> run('datapack disable '+name);
datapack_enable(name) -> run('datapack enable '+name);
//I dont have priority things here hope nobody cares
//datapack_list() just prints in chat

//debug start and stop just print in chat

defaultgamemode(gamemode) -> run('defaultgamemode '+gamemode);
//gamemode must be: survival|creative|spectator|adventure

deop(target) -> run('deop '+target);

difficulty(difficulty) -> run('difficulty '+difficulty);
//difficulty must be easy|hard|normal|peaceful

//effect()  use the built in  modify(e, 'effect' ...

enchant(target,enchantment,level) -> run('enchant '+target+' '+enchant+' '+level);

//execute is too complicated ill just do a few common options
execute_as(target,expression) -> run('execute as '+target+' run '+expression);

experience_add(target,amount,type) -> run('experience add '+target+' '+amount+' '+type);
experience_set(target,amount,type) -> run('experience set '+target+' '+amount+' '+type);
//type must be: levels|points

//fill()  use scan() + set()

forceload_add(pos1,pos2) -> run('forceload add '+pos1[0]+' '+pos1[1]+' '+pos2[0]+' '+pos2[1]);
forceload_remove(x1,y1,x2,y2) -> run('forceload remove '+pos1[0]+' '+pos1[1]+' '+pos2[0]+' '+pos2[1]);
//pos1 and pos2 are lists with two coords, not chunk coords, but real ones
forceload_remove_all() -> run('forceload remove all');
//forceload_query just prints to chat

function(name) -> run('function '+name);

//gamemode  use modify(e, 'gamemode')

gamerule(name,value) -> run('gamerule '+name+' '+value);

give(target,item,count) -> run('give '+target+' '+item+' '+count);

//help is just chat messages

kick(target,reason) -> run('kick '+target+' '+reason);

kill(target) -> run('kill '+target);

//list is just chat messages

//locate is just cht messages

//locatebiome is just cht messages

loot(target,source) -> run('loot '+target+' '+source);

me(message) -> run('me '+message);

//msg  use tell()

op(target) -> run('op '+target);

pardon(target) -> run('pardon '+target);
pardonip(target) -> run('pardon-ip '+target);

//particle  use built-in particle()

//playsound  use built-in sound()

publish() -> run('publish');
publish_to_port(port) -> run('publish '+port);
//singleplayer-only

recipe_give(target,recipe) -> run('recipe give '+target+' recipe');
recipe_take(target,recipe) -> run('recipe take '+target+' recipe');
//recipe must be: * [everything] or a recipe like crafing_table

reload() -> run('reload');

//replaceitem  use built-in inventory_set() and inventory_remove(), etc

//saveall  use built-in save()
saveon() -> run('save-on');
saveoff() -> run('save-off');

//say  is useless

//schedule() is built-in

//scoreboard() is built-in

//seed() is built-in

//setblock is built-in as set()

setidletimeout(minutes) -> run('setidletimeout '+minutes);

setworldspawn(pos) -> run('setworldspawn '+pos[0]+' '+pos[1]+' '+pos[2]);
setworldspawn_angle(pos,angle) -> run('setworldspawn '+pos[0]+' '+pos[1]+' '+pos[2]+' '+angle[0]+' '+angle[1]);
//setworldspawn_angle is only avalible on 1.16.2
//pos must be a list

spawnpoint(target,pos) -> run('spawnpoint 'target+' '+pos[0]+' '+pos[1]+' '+pos[2]);
spawnpoint_angle(target,pos,angle) -> run('spawnpoint 'target+' '+pos[0]+' '+pos[1]+' '+pos[2]+' '+angle[0]+' '+angle[1]);
//spawnpoint_angle is only avalible on 1.16.2
//pos must be a list

spectate(target,spectator) -> run('spectate '+target+' '+spectator);

spreadplayers(center,spread_distance,max_range,respect_teams,targets) -> run('spreadplayers '+center+' '+spread_distance+' '+max_range+' '+respect_teams+' 'targets);
spreadplayers_under(center,spread_distance,max_range,max_height,respect_teams,targets) -> run('spreadplayers '+center+' '+spread_distance+' '+max_range+' under '+max_height+' '+respect_teams+' 'targets);

stop() -> run('stop');

//stopsound  use built-in sound()

//summon  use built-in spawn()

//tag  use built-in modify(e, 'tag') and modify(e, 'clear_tag')

//team  you can query 'team' but not set it...
team_add(team,displayname) -> run('team add '+team+' '+displayname);
team_empty(team) -> run('team empty '+team);
team_join(team,target) -> run('team join '+team+' '+target);
team_leave(target) -> run('team leave '+target);
team_modify(team,option,value) -> run('team modify '+team+' '+option+' '+value);
team_remove(team) -> run('team remove '+team);

//teammsg  try: run(execute as player run teammsg message)

teleport_player(target,destination_player) -> run('teleport '+target+' '+destination_player);
teleport_pos(target,pos) -> run('teleport '+target+' '+pos[0]+' '+pos[1]+' '+pos[2]);
teleport_pos_rotation(target,pos,rotation) -> run('teleport '+target+' '+pos[0]+' '+pos[1]+' '+pos[2]+' '+rotation[0]+' '+rotation[1]);
teleport_pos_facing_loction(target,pos,facing_pos) -> run('teleport '+target+' '+pos[0]+' '+pos[1]+' '+pos[2]+' facing '+facing_pos[0]+' '+facing_pos[1]+' '+facing_pos[2]);
teleport_pos_facing_entity_eyes(target,pos,entity) -> run('teleport '+target+' '+pos[0]+' '+pos[1]+' '+pos[2]+' facing entity '+entity+' eyes');
teleport_pos_facing_entity_feet(target,pos,entity) -> run('teleport '+target+' '+pos[0]+' '+pos[1]+' '+pos[2]+' facing entity '+entity+' feet');

tell(target,message) -> run('tell '+target+' '+message);

tellraw(target,json) -> (run('tellraw '+target+' '+json));
tellPlayer(target,text) -> (__tellraw(target,'"'+text+'"'));

world_time_add(time_num) -> run('time add '+time_num);
world_time_set(time) -> run('time set '+time);
//time in world_time_set can be a int 0 to 2^31-1, day|night|noon|midnight
//time() is built-in and different, so I've called this world_time

display_title(target,location,title) -> run('title '+target+' '+location+' '+title);
//location can be title|subtitle|actionbar
display_title_clear(target) -> run('title '+target+'clear');
display_title_reset(target) -> run('title '+target+'reset');
display_title_times(target,fade_in,stay,fade_out) -> run('title '+target+' times '+fade_in+' '+stay+' '+fade_out);
//title() is built-in, so I've called this display_title()

//tp  use teleport()

trigger(target,objective) -> run('execute as '+target+' run trigger '+objective);
trigger_add(target,objective,value) -> run('execute as '+target+' run trigger '+objective+' add '+value);
trigger_set(target,objective,value) -> run('execute as '+target+' run trigger '+objective+' set '+value);
//idk why you'd not use scoreboard commands but here's trigger ones
//trigger  instead use built-in scoreboard commands 

//w use tell
weather(type) -> run('weather '+type);
weather_duration(type,duration) -> run('weather '+type+' '+duration);
//type must be: clear|rain|thunder

whitelist_add(target) -> run('whitelist add '+target);
whitelist_off() -> run('whitelist off');
whitelist_on() -> run('whitelist on');
whitelist_reload() -> run('whitelist reload'); //what are you doing that needs this lol
whitelist_remove(target) -> run'whitelist remove '+target);

worldborder_add(distance,time) -> run('worldborder add '+distance+' '+time);
worldborder_center(pos) -> run('worldborder center '+pos[0]+' '+pos[1]);
//pos is a list of x and y 
worldborder_damage_amount(damage_per_block) -> run('worldborder damage amount '+damage_per_block);
worldborder_damage_buffer(distance) -> run('worldborder damage buffer '+distance);
worldborder_set(diameter,time) -> run('worldborder set '+diameter+' '+time);
worldborder_warning_distance(warning_distance) -> run('worldborder warning distance '+warning_distance);
worldborder_warning_time(warning_time) -> run('worldborder warning time '+warning_time);

//xp  use experience()