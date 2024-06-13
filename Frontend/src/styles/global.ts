import { createGlobalStyle } from "styled-components";

export const GlobalStyle = createGlobalStyle`
  :root { //Variáveis do css para definir cores:
    --red: #e52e4d;
    --green: #33cc95;
    --blue: #5429cc;

    --blue-light: #6933ff;

    --text-title: #363f5f;
    --text-body: #969cb3;

    --shape: #ffffff;
    --background: #f0f2f5;
  }

  //Tamanho de fonte da aplicação padrão vem como 16px, funciona muito bem para Desktop. Porém para outros dispostivos pode não ser a melhor opção.
  html {
    @media (max-width: 1080px) { //Para uma tela até 1080px de largura o font-size será diminuido para 15px
      font-size: 93.75%; //15px
    }

    @media (max-width: 720px) { //Para uma tela até 720px de largura o font-size será diminuido para 14px 
      font-size: 87.5%; //14px
    }
  }

  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  body {
    background: var(--background);
    -webkit-font-smoothing: antialiased;  //fontes mais nítidas e detalhadas
  }

  body, input, textarea, button {
    font-family: 'Poppins', sans-serif;
    font-weight: 400;
  }

  h1 , h2 , h3, h4, h5, h6, strong {
    font-weight: 600;
  }

  button {
    cursor: pointer;
  }

  [disabled] {
    opacity: 0.6;
    cursor: not-allowed;
  }

  .react-modal-overlay {
    background-color: rgba(0, 0, 0, 0.5);

    position: fixed;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;

    display: flex;
    align-items: center;
    justify-content: center;
  }

  .react-modal-content {
    width: 100%;
    max-width: 576px;
    background-color: var(--background);
    padding: 3rem;
    position: relative;
    border-radius: 0.25rem;
  }

  .react-modal-close {
    position: absolute;
    right: 1.5rem;
    top: 1.5rem;
    border: 0;
    background-color: transparent;

    transition: filter 0.2s;

    &:hover {
      filter: brightness(0.8);
    }
  }
`;