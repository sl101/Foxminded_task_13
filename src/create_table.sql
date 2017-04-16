-- Table: public.schedule

-- DROP TABLE public.schedule;

CREATE TABLE public.schedule
(
  id integer NOT NULL DEFAULT nextval('schedule_id_seq'::regclass),
  weekday character varying,
  "time" character varying,
  event character varying,
  CONSTRAINT schedule_unique UNIQUE (weekday, "time", event)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.schedule
  OWNER TO postgres;
