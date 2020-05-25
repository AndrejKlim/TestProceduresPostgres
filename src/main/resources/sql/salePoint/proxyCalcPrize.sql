create or replace function proxyCalcPrize(OUT f int) returns int
language plpgsql
as
    $$
    begin
        select count(*) into f from "testTaskDB".public.calcprize();
    end;
    $$