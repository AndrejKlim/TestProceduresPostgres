create or replace function proxyAlterCalcPrize(out f int) returns int
language plpgsql
as
    $$
    begin
        select count(*) into f from "testTaskDB".public.altercalcprize();
    end;
    $$