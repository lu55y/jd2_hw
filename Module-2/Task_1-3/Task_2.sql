select expenses.num,paydate,name,value from listexpenses.expenses,listexpenses.receivers
 where  receiver=receivers.num and value>10000;