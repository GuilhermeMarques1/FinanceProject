import { useTransaction } from "../../hooks/useTransaction";

import incomeImg from "../../assets/income.svg";
import outcomeImg from "../../assets/outcome.svg";
import totalImg from "../../assets/total.svg";

import { Container } from "./styles";

export function Summary() {
  const { transactions } = useTransaction();

  const summary = transactions.reduce((acc, transaction) => {
    if(transaction.recurrence === 'unique') {
      if(transaction.type === 'deposit') {
        acc.deposits += transaction.amount;
        acc.total += transaction.amount;
        return acc;
      } else {
        acc.withdraws -= transaction.amount;
        acc.total -= transaction.amount;
        return acc;
      }
    } else {
      if(transaction.type === 'deposit') {
        acc.depositsRec += transaction.amount;
        acc.total += transaction.amount;
        return acc;
      } else {
        acc.withdrawsRec -= transaction.amount;
        acc.total -= transaction.amount;
        return acc;
      }
    }
  }, {
    deposits: 0,
    withdraws: 0,
    total: 0,
    depositsRec: 0,
    withdrawsRec: 0,
  });

  return(
    <Container>
      <div>
        <header>
          <p>Entradas Únicas</p>
          <img src={incomeImg} alt="income icon" />
        </header>
        <strong>
          {
            new Intl.NumberFormat('pt-BR', {
              style: 'currency',
              currency: 'BRL'
            }).format(summary.deposits)
          }
        </strong>
      </div>

      <div>
        <header>
          <p>Saídas Únicas</p>
          <img src={outcomeImg} alt="outcome icon" />
        </header>
        <strong>
          {
            new Intl.NumberFormat('pt-BR', {
              style: 'currency',
              currency: 'BRL',
            }).format(summary.withdraws)
          }
        </strong>
      </div>

      <div>
        <header>
          <p>Entradas recorrentes</p>
          <img src={incomeImg} alt="income icon" />
        </header>
        <strong>
          {
            new Intl.NumberFormat('pt-BR', {
                style: 'currency',
                currency: 'BRL',
              }).format(summary.depositsRec)
          }
        </strong>
      </div>

      <div>
        <header>
          <p>Saídas Recorrentes</p>
          <img src={outcomeImg} alt="outcome icon" />
        </header>
        <strong>
          {
            new Intl.NumberFormat('pt-BR', {
                style: 'currency',
                currency: 'BRL',
              }).format(summary.withdrawsRec)
          }
        </strong>
      </div>

      <div className="highlight-background">
        <header>
          <p>Saldo Total</p>
          <img src={totalImg} alt="total" />
        </header>
        <strong>
          {
            new Intl.NumberFormat('pt-BR', {
              style: 'currency',
              currency: 'BRL',
            }).format(summary.total)
          }
        </strong>
      </div>
    </Container>
  );
}