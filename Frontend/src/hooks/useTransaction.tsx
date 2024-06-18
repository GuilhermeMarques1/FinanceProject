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
  recurrence: string
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
        'Authorization': `Bearer ${localStorage.getItem('authToken')}`
      }
    })
      .then((response) => {
        const { operacoesRecorrentes, operacoesUnicas } = response.data;

        console.log(response.data);

        const formattedTransactionsUnicas: Transaction[] = operacoesUnicas.map((op: any) => {
          return {
            id: op.id,
            title: op.nome,
            amount: op.valor,
            category: op.descricao,
            createAt: op.data,
            type: op.type,
            recurrence: 'unique'
          } as Transaction
        })

        const formattedTransactionsRecorrentes: Transaction[] = operacoesRecorrentes.map((op: any) => {
          return {
            id: op.id,
            title: op.nome,
            amount: op.valor,
            category: op.descricao,
            createAt: op.dataInicial,
            type: op.type,
            recurrence: 'recurrence'
          } as Transaction
        })

        setTransactions([...formattedTransactionsUnicas, ...formattedTransactionsRecorrentes])
      })
  
  }, [])

  async function createTransaction(transactionInput: TransactionInput) {
    // const response = await api.post('/transactions', {
    //   ...transactionInput,
    //   createAt: new Date(),
    // });
    // const { transaction } = response.data;

    const recurrence = transactionInput.recurrence === 'unique' ? 'unica' : 'recorrente';
    await api2.post(`/operacao/${recurrence}`, {
      nome: transactionInput.title,
      descricao: transactionInput.category,
      valor: transactionInput.amount,
      type: transactionInput.type,
      data: new Date().toISOString().slice(0, 19)
    }, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('authToken')}`
      }
    })


    setTransactions([
      ...transactions,
      // transaction
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
