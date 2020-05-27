create or replace function proxyAlterCalcPrize(in recordQuarter_in int,out f int) returns int
language plpgsql
as
    $$
    begin
        select count(*) into f from "testTaskDB".public.altercalcprize(recordQuarter_in);
    end;
    $$