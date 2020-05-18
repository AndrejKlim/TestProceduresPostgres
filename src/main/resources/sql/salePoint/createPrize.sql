create or replace function createPrize(IN salePointId bigint, IN prize_in int, IN operationTime int) returns void
language plpgsql
as
    $$
    begin
        insert into "testTaskDB".public.sale_point_prize (sale_point_id, prize, operation_time) values (salePointId, prize_in, operationTime);
    end;
    $$