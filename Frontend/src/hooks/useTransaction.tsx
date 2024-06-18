import { createContext, useEffect, useState, ReactNode, useContext } from "react";
import { api } from "../services/api";
import { api2 } from "../services/api2";

interface Transaction {
  id: number,
  title: string,
  type: string, 
  amount: number,
  category: string,
  createAt: string,
}

type TransactionInput = Omit<Transaction, 'id' | 'createAt'>

interface TransactionProviderProps {
  children: ReactNode
}

interface TransactionContextData {
  transactions: Transaction[],
  createTransaction: (transaction: TransactionInput) => Promise<void>,
}

const TransactionsContext = createContext<TransactionContextData>({} as TransactionContextData);

export function TransactionsProvider({ children }: TransactionProviderProps) {
  const [transactions, setTransactions] = useState<Transaction[]>([]);

  useEffect(() => {

    api2.get("/operacao/", {
      headers: {
        'Authorization': `Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6Imd1aUB1bmVzcC5iciIsImV4cCI6MTcxODc0NDA2NX0.3Ikt617MA-i6oq5kG0zIh7v-x3ZANLixe_KZitIhGVE`
      }
    })
      .then((response) => {
        const { operacoesRecorrentes, operacoesUnicas } = response.data;

        const formattedTransactionsUnicas: Transaction[] = operacoesUnicas.map((op: any) => {
          return {
            id: op.id,
            title: op.nome,
            amount: op.valor,
            category: op.descricao,
            createAt: '123',
            type: 'deposit'
          } as Transaction
        })

        const formattedTransactionsRecorrentes: Transaction[] = operacoesRecorrentes.map((op: any) => {
          return {
            id: op.id,
            title: op.nome,
            amount: op.valor,
            category: op.descricao,
            createAt: '123',
            type: 'deposit'
          } as Transaction
        })

        setTransactions([...formattedTransactionsUnicas, ...formattedTransactionsRecorrentes])
      })
  
  }, [])

  async function createTransaction(transactionInput: TransactionInput) {
    const response = await api.post('/transactions', {
      ...transactionInput,
      createAt: new Date(),
    });
    const { transaction } = response.data;

    setTransactions([
      ...transactions,
      transaction
    ]);
  }

  return (
    <TransactionsContext.Provider value={{ transactions, createTransaction }}>
      { children }
    </TransactionsContext.Provider>
  );
}

export function useTransaction() {
  const context = useContext(TransactionsContext);

  return context;
}
