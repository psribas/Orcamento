CREATE OR REPLACE TRIGGER tb_orcamento_T1
  BEFORE DELETE OR INSERT OR UPDATE ON tb_orcamento
  FOR EACH ROW

DECLARE
    v_id number;
BEGIN
   SELECT TB_ORCAMENTO_s.nextval
     INTO v_id FROM dual;
   --
   :new.id := v_id;
END;