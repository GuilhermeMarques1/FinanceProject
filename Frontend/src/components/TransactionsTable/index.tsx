import { useTransaction } from "../../hooks/useTransaction";

import { Container } from "./styles";

export function TransactionsTable() {
  const { transactions } = useTransaction();

  return(
    <Container>
      <table>
        <thead>
          <tr>
            <th>Titulo</th>
            <th>Valor</th>
            <th>Categoria</th>
            <th>Data</th>
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
              </tr>
            );
          })}
        </tbody>
      </table>
    </Container>
  );
}