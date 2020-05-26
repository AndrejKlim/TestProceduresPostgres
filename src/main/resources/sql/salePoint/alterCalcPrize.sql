create or replace function alterCalcPrize() returns void
language plpgsql
as
    $$
        declare sumPrize int;

        declare
            sim1000 int := 100;
            sim3000 int := 500;
            modem500 int := 100;
            modem1000 int := 500;
            tv300 int := 100;
            tv600 int :=500;


    begin
        insert into "testTaskDB".public.sale_point_prize(sale_point_id, prize)
        select id, case --set prize according to sales
        when sold_sim >= 3000 and sold_modem >= 1000 and sold_tv_adapters >= 600
        then sim3000 + modem1000 + tv600
        --all max values checked
        when sold_sim >= 1000 and sold_sim < 3000 and sold_modem >= 500 and sold_modem < 1000 and sold_tv_adapters >= 300 and sold_tv_adapters < 600
        then sim1000 + modem500 + tv300
        --all mid values checked
        when sold_sim < 1000 and sold_modem < 500 and sold_tv_adapters < 300
        then 0
        -- all low values checked
        when sold_sim >= 1000 and sold_sim < 3000 and sold_modem >= 1000 and sold_tv_adapters >= 600
        then sim1000 + modem1000 + tv600
        when sold_sim >= 3000 and sold_modem >= 500 and sold_modem <= 1000 and sold_tv_adapters >= 600
        then sim3000 + modem500 + tv600
        when sold_sim >= 3000 and sold_modem >= 1000 and sold_tv_adapters >= 300 and sold_tv_adapters < 600
        then sim3000 + modem1000 + tv300
        --all with 2 max and 1 mid checked
        when sold_sim >= 1000 and sold_sim < 3000 and sold_modem >= 500 and sold_modem < 1000 and sold_tv_adapters >= 600
        then sim1000 + modem500 + tv600
        when sold_sim >= 1000 and sold_sim < 3000 and sold_modem >= 1000 and sold_tv_adapters >= 300 and sold_tv_adapters < 600
        then sim1000 + modem1000 + tv300
        when sold_sim >= 3000 and  sold_modem >= 500 and sold_modem < 1000 and sold_tv_adapters >= 300 and sold_tv_adapters < 600
        then sim3000 + modem500 + tv300
        --all with 1 max and 2 mid values checked
        when sold_sim >= 3000 and sold_modem >= 1000 and sold_tv_adapters < 300
        then sim3000 + modem1000
        when sold_sim >= 3000 and sold_modem < 500 and sold_tv_adapters >= 600
        then sim3000 + tv600
        when sold_sim < 1000 and sold_modem >= 1000 and sold_tv_adapters >= 600
        then modem1000 + tv600
        --all with 2 max and 1 low checked
        when sold_sim >= 3000 and sold_modem < 500 and sold_tv_adapters < 300
        then sim3000
        when sold_sim < 1000 and sold_modem >= 1000 and sold_tv_adapters < 300
        then modem1000
        when sold_sim < 1000 and sold_modem < 500 and sold_tv_adapters >= 600
        then tv600
        --all with 1 max and 2 low checked
        when sold_sim >= 1000 and sold_sim < 3000 and sold_modem >= 500 and sold_modem < 1000 and sold_tv_adapters < 300
        then sim1000 + modem500
        when sold_sim >= 1000 and sold_sim < 3000 and sold_modem < 500 and sold_tv_adapters >= 300 and sold_tv_adapters < 600
        then sim1000 + tv300
        when sold_sim < 1000 and sold_modem >= 500 and sold_modem < 1000 and sold_tv_adapters >= 300 and sold_tv_adapters < 600
        then modem500 + tv300
        --all with 2 mid and 1 low checked
        when sold_sim >= 1000 and sold_sim < 3000 and sold_modem < 500 and sold_tv_adapters < 300
        then sim1000
        when sold_sim < 1000 and sold_modem >= 500 and sold_modem < 1000 and sold_tv_adapters < 300
        then modem500
        when sold_sim < 1000 and sold_modem < 500  and sold_tv_adapters >= 300 and sold_tv_adapters < 600
        then tv300
        --all with 1 mid and 2 low checked
        when sold_sim >= 3000 and sold_modem >=500 and sold_modem < 1000 and sold_tv_adapters < 300 -- max sim
        then sim3000 + modem500
        when sold_sim >= 3000 and sold_modem < 500 and sold_tv_adapters >= 300 and sold_tv_adapters < 600 -- max sim
        then sim3000 + tv300
        when sold_sim >= 1000 and sold_sim < 3000 and sold_modem >= 1000 and sold_tv_adapters < 300 --max modem
        then sim1000 + modem1000
        when sold_sim < 1000 and sold_modem >= 1000 and sold_tv_adapters >= 300 and sold_tv_adapters < 600 -- max modem
        then modem1000 + tv300
        when sold_sim >= 1000 and sold_sim < 3000 and sold_modem < 500 and sold_tv_adapters >= 600 -- max adapters
        then sim1000 + tv600
        when  sold_sim < 1000 and sold_modem >=500 and sold_modem < 1000 and sold_tv_adapters >= 600 -- max adapters
        then modem500 + tv600
        --all with 1 max 1 mid and 1 low checked
        else 4444 end
        from "testTaskDB".public.sale_point ;

    end;
    $$