SELECT paydate,max(value),name FROM listexpenses.expenses,listexpenses.receivers 
where receiver=receivers.num and  paydate=(select paydate from listexpenses.expenses 
where value=(select max(value) from listexpenses.expenses)); 