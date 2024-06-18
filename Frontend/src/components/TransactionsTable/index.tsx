import { useTransaction } from "../../hooks/useTransaction";

import { Container } from "./styles";

export function TransactionsTable() {
  const { transactions } = useTransaction();

  console.log(transactions)

  return(
    <Container>
      <table>
        <thead>
          <tr>
            <th>Titulo</th>
            <th>Valor</th>
            <th>Categoria</th>
            <th>Data</th>
            <th>Tipo</th>
          </tr>
        </thead>

        <tbody>
          {transactions.map((transaction) => {
            return (
              <tr key={transaction.title}>
                <td>{transaction.title}</td>
                <td className={transaction.type}>
                  {
                    new Intl.NumberFormat("pt-BR", {
                      style: "currency",
                      currency: "BRL"
                    }).format(transaction.amount)
                  }
                </td>
                <td>{transaction.category}</td>
                <td>
                  {
                    Intl.DateTimeFormat("pt-BR").format(
                      new Date(transaction.createAt)
                    )
                  }
                </td>
                <td>
                  {
                    transaction.recurrence === "unique" ? "Única" : "Recorrente"
                  }
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </Container>
  );
}