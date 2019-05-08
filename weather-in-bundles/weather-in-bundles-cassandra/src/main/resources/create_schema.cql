
create keyspace if not exists weather WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1' };

use weather;

create table if not exists weather.main (
    time_stamp timestamp primary key,
    humidity double,
    pressure double,
    temp double);

create table if not exists weather.wind (
    time_stamp timestamp primary key,
    speed double);

create table if not exists weather.weather (
    time_stamp timestamp primary key,
    place text);