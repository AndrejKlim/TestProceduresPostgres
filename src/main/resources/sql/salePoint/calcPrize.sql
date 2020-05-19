create or replace function calcPrize() returns void
    language plpgsql
as
$$
begin
    insert into "testTaskDB".public.sale_point_prize as spp(sale_point_id, prize)
    select id, 100 from "testTaskDB".public.sale_point where sold_sim >= 1000 and sold_sim < 3000
    on conflict (sale_point_id) do update set prize = spp.prize + 100;

    insert into "testTaskDB".public.sale_point_prize as spp1  (sale_point_id, prize)
    select id, 500 from "testTaskDB".public.sale_point  where sold_sim >= 3000
    on conflict (sale_point_id) do update  set prize = spp1.prize + 500;

    insert into "testTaskDB".public.sale_point_prize as spp2 (sale_point_id, prize)
    select id, 100 from "testTaskDB".public.sale_point where sold_modem >= 500 and sold_modem < 1000
    on conflict (sale_point_id) do update set prize = spp2.prize + 100;

    insert into "testTaskDB".public.sale_point_prize as spp3 (sale_point_id, prize)
    select id, 500 from "testTaskDB".public.sale_point where sold_modem >= 1000
    on conflict (sale_point_id) do update set prize = spp3.prize + 500;

    insert into "testTaskDB".public.sale_point_prize as spp4 (sale_point_id, prize)
    select id, 100 from "testTaskDB".public.sale_point where sold_tv_adapters >= 300 and sold_modem < 600
    on conflict (sale_point_id) do update set prize = spp4.prize + 100;

    insert into "testTaskDB".public.sale_point_prize  as spp5 (sale_point_id, prize)
    select id, 100 from "testTaskDB".public.sale_point where sold_tv_adapters >= 600
    on conflict (sale_point_id) do update set prize = spp5.prize + 500;
end;
$$