All text files should be located inside the mimi2 folder in order to function properly.
the mimi2 folder can be located where you choose; but all files ond folders inside cannot be moved or renamed.

***Textfile Overview***

All files should be ordered in least-to-greatest so that the ID and array listing are identical.

Items - This file contains all the items for the mimi2 game; they are formated in the following order:
	ID, Name, Description, Use (A = attack, H = heal, K = key followed by the amount or keyID), Uses


Navigation - This file contains the mapping between the rooms through directions. they are formated in the following way; roomID; followed by
	a key-value pair of the String direction name and the corresponding roomID. A room can have multiple direction and the delimited when the system reads a new int.

Rooms - This file contains the individual rooms for the game; they are formated in the following order: roomID, Name, Description. Descriptions must be on a single line
	and escape characters (\n) used to indicate a new line.