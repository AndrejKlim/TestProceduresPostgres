create or replace function calcPrize() returns void
language plpgsql
as
    $$
    begin
        insert into "testTaskDB".public.sale_point_prize (sale_point_id, prize)
        select id, 100 from "testTaskDB".public.sale_point where sold_sim >= 1000 and sold_sim < 3000
        on conflict DO NOTHING ;
        insert into "testTaskDB".public.sale_point_prize (sale_point_id, prize)
        select id, 500 from "testTaskDB".public.sale_point where sold_sim >= 3000
        on conflict (sale_point_id)
            do UPDATE set prize=500;
--         update "testTaskDB".public.sale_point_prize SET prize = 500 where sale_point_id in (
--             select id from "testTaskDB".public.sale_point where sold_sim >= 3000
--             );
--         select id from "testTaskDB".public.sale_point where sold_sim >= 3000;
--         insert into "testTaskDB".public.sale_point_prize(sale_point_id, prize) VALUES (id, 500);
    end;
    $$