Feature: Validar pesquisa válida Blog Agibank

  Scenario: Validar acesso ao card de beneficios
    Given que eu abro a página "https://blog.agibank.com.br/#"
    When clicar na barra de pesquisa
    And digitar no campo de pesquisa "Benefícios"
    Then os artigos referentes a pesquisa deverão ser carregados

  Scenario: Validar acesso ao card de produtos
    Given que eu esteja na página atual
    When clicar na barra de pesquisa
    And digitar no campo de pesquisa "piquis"
    Then deverá ser possível visualizar uma mensagem amigável ao cliente informando que não há resultados