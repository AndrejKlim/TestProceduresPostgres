create or replace function createUser(in salary_in int) returns void
language plpgsql

as
    $$
        begin
        insert into "testTaskDB".public.emp (name, role, salary) values ('Valentin Pikul', 'Writer', salary_in);
        end;
    $$