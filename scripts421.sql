alter table student add constraint  check_age check (age >= 16);

alter table student add constraint unique_name unique (name);

alter table faculty add constraint unique_name_color unique (name, color);

alter table student add constraint constraint_age check (age is null) default 20;