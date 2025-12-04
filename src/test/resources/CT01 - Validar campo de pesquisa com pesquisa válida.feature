Feature: Validar pesquisa válida Blog Agibank

  Scenario: Validar acesso ao card de produtos Pix
    Given que eu abro a página "https://blog.agibank.com.br/#"
    When clicar na barra de pesquisa
    And digitar no campo de pesquisa "Pix"
    Then deverá aparecer o card "Tudo sobre o Pix Parcelado: entenda como irá funcionar"
