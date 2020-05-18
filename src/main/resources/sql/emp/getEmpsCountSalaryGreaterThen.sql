create or replace function getEmpsCountBySalaryGreaterThen(IN salary_in int, OUT count_out int)
language plpgsql
as
    $$
    begin
        select count(*) into count_out from "testTaskDB".public.emp where salary >= salary_in;
    end;
    $$

