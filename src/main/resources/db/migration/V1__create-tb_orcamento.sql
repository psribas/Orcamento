create table TB_ORCAMENTO
(
  id          NUMBER,
  nome        VARCHAR2(240),
  categoria   VARCHAR2(100),
  valor       NUMBER,
  status      VARCHAR2(100),
  datacriacao VARCHAR2(10)
)
tablespace SYSAUX
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );