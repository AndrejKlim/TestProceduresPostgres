create or replace function createUser() returns void
language plpgsql

as
    $$
        begin
        insert into "testTaskDB".public.emp (name, role, salary) values ('Valentin Pikul', 'Writer', 400);
        end;
    $$