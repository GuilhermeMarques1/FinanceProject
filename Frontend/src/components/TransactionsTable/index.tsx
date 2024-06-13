// import { useEffect, useState } from "react";
// import { api } from "../../services/api";
import { useTransaction } from "../../hooks/useTransaction";

import { Container } from "./styles";

export function TransactionsTable() {
  // useEffect(() => {
  //   fetch("http:localhost:3000/api/transactions")
  //     .then(response => response.json())
  //     .then(data => console.log(data));
  // }, []);

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
              <tr key={transaction.id}>
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

        {/* <tbody>
          <tr>
            <td>Desenvolvimento de website</td>
            <td className="deposit">R$12.000</td>
            <td>Desenvolvimento</td>
            <td>18/10/2022</td>
          </tr>
          <tr>
            <td>Aluguel</td>
            <td className="withdraw">- R$840,00</td>
            <td>Casa</td>
            <td>10/10/2022</td>
          </tr>
          <tr>
            <td>Ajuda trabalho de TI</td>
            <td className="deposit">R$150</td>
            <td>Desenvolvimento</td>
            <td>16/10/2022</td>
          </tr>
        </tbody> */}
      </table>
    </Container>
  );
}