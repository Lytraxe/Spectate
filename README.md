# Spectate

  Simple and easy to use tool that allows players to spectate minigames that dont have their own method of allowing players to spectate.

## Installation

  Simply drop the compiled jar file to your server's `/plugin` folder

## Usage

  Use `/spect add <name>` command to define an arena at that point. It will save the arena along with the location to `/plugin/Spectate/config.yml` file (will change it soon) (try to avoid editing the file manually cuz there is no check to validate the file as for now). 
  
  Players can use `/spect list` to view a list of currently available arenas and `/spect <name>` to go to the arena. If you want players to be unable to leave the arena, create a world guard region and set the flag `exit` to Deny. use world guard region to deny some commands for now.
  
  Use `/spect exit` to exit current arena, and restore inventory, location and flight.

## Commands

### Require `spectate.use` permission
- **/spect** - Root command, does nothing without arguments
- **/spect (name)** - Teleport to spectate the arena
- **/spect exit** - Exit current arena 
- **/spect list** - List currently available arenas

### Require `spectate.admin` permission
- **/spect add (name)** - Create an arena at current location
- **/spect remove (name)** - Remove the arena

