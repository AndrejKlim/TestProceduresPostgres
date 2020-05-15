create or replace function getName(id_in integer) returns text
    language plpgsql
as
$$
declare name_out text;
begin
    select name  from "testTaskDB".public.emp where id=id_in into name_out;
    return name_out;
end
$$;

alter function getname(integer) owner to postgres;
