There are 3 kind of migrations

Versioned
- 
For example V2018.11.15.1__Initial.sql, the version number here needs to be both unique and ordered.

Undo
-
For example U2018.11.15.1__Initial.sql, these directly correspond to the above counterpart, but undoes the migration.

Repeatable
-
For example R__DevSeed.sql, this needs to be something that can be ran after all the versioned migrations, this is good for seeding data into the database.

Locations
-
All production aimed migrations go in this folder, any specifically for development (such as a devseed for adding fake data) needs to go in a development sub folder.
