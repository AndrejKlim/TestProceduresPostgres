create or replace function sumSalary(out sum_out bigint) returns bigint
language plpgsql
as
    $$
    begin
        select sum(salary) into sum_out from "testTaskDB".public.emp;
    end;
    $$