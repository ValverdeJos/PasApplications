let data2; 
const editForm = document.createElement('form');

function fetchData() {
    fetch('http://localhost:5018/Product')
        .then(response => response.json())
        .then(data => {

            console.log(data);
            data2 = data;

            const tabela = document.getElementById('tabela');

            
            tabela.innerHTML = '';
            
            

            
            if (data.length > 0) {
                const cabecalho = document.createElement('tr');
                for (const chave in data[0]) {
                    if (data[0].hasOwnProperty(chave)) {
                        const th = document.createElement('th');
                        th.textContent = chave;
                        cabecalho.appendChild(th);
                    }
                }
                tabela.appendChild(cabecalho);


                data.forEach(item => {
                    const row = document.createElement('tr');
                    for (const chave in item) {
                        if (item.hasOwnProperty(chave)) {
                            const cell = document.createElement('td');
                            cell.textContent = item[chave];
                            row.appendChild(cell);
                        }
                    }

                    const deleteCell = document.createElement('td');
                    const deleteIcon = document.createElement('i');
                    deleteIcon.className = 'fas fa-trash-alt';
                    deleteIcon.style.cursor = 'pointer';
                    deleteIcon.addEventListener('click', () => deleteItem(item.id));
                    deleteCell.appendChild(deleteIcon);
                    row.appendChild(deleteCell);

                    const editCell = document.createElement('td');
                    const editIcon = document.createElement('i');
                    editIcon.className = 'fas fa-pencil-alt';
                    editIcon.style.cursor = 'pointer';
                    editIcon.addEventListener('click', () => editItem(item.id));
                    editCell.appendChild(editIcon);
                    row.appendChild(editCell);
                    //console.log(item.id)

                    tabela.appendChild(row);
                });
            } else {

                tabela.innerHTML = '<tr><td colspan="3">Nenhum dado disponível.</td></tr>';
            }

        })
        .catch(error => {
            console.error('Erro ao buscar dados da API:', error);
        });
}

function deleteItem(itemId) {
    fetch(`http://localhost:5018/ProductDelete/${itemId}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {

                fetchData();
            } else {
                console.error('Erro ao excluir o item.');
            }
        })
        .catch(error => {
            console.error('Erro ao excluir o item:', error);
        });
}


function editItem(itemId) {



    const itemToEdit = data2.find(item => item.id === itemId);

    console.log(itemToEdit);

    if (itemToEdit) {

        editForm.setAttribute('data-editing-id', itemId);



        editForm.innerHTML = '';

        for (const chave in itemToEdit) {
            if (itemToEdit.hasOwnProperty(chave)) {
                const label = document.createElement('label');
                label.textContent = `${chave}: `;
                const input = document.createElement('input');
                input.type = 'text';
                input.name = chave;
                input.value = itemToEdit[chave];

                label.appendChild(input);
                editForm.appendChild(label);
            }
        }


        const submitButton = document.createElement('button');
        submitButton.textContent = 'Update';
        submitButton.addEventListener('click', () => {

            const updatedData = {};


            for (const chave in itemToEdit) {
                if (itemToEdit.hasOwnProperty(chave)) {
                    const input = editForm.querySelector(`input[name="${chave}"]`);
                    if (chave === 'id' || chave === 'mesPremium' || chave === 'priority' || chave === 'nameProduct') {
                        updatedData[chave] = parseInt(input.value, 10); 
                    } else {
                        updatedData[chave] = input.value;
                    }
                }
            }



            console.log(JSON.stringify(updatedData))

                setTimeout(function(){
                    fetch(`http://localhost:5018/ProductPut/${itemId}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(updatedData),
                    })
                        .then(response => {
                            console.log(response);
                            if (response.ok) {
                                fetchData();
                                editForm.reset();
                                editForm.removeAttribute('data-editing-id');
                            } else {
                                console.error('Erreur lors de la mise à jour.');
                            }
                        })
                        .catch(error => {
                            console.error('Erreur lors de la mise à jour :', error);
                        });
                },7000)
            
                
            
            
            
        });
        editForm.appendChild(submitButton);


        if (!document.contains(editForm)) {
            document.body.appendChild(editForm);
        }
    } else {
        console.error('Élément introuvable pour la mise à jour.');
    }
}


function openNav() {
    document.getElementById("mySidenav").classList.add("open");
}

function closeNav() {
    document.getElementById("mySidenav").classList.remove("open");
}


function openPopup() {
    const popup = document.getElementById('popup');
    popup.style.display = 'block';
}


function closePopup() {
    const popup = document.getElementById('popup');
    popup.style.display = 'none';
}

function generateRandomIdCompra() {
    const maxDigits = 6;
    const min = Math.pow(10, maxDigits - 1);
    const max = Math.pow(10, maxDigits) - 1;
    const randomIdCompra = Math.floor(Math.random() * (max - min + 1)) + min;
    return randomIdCompra.toString().padStart(maxDigits, '0');
}



function addNewProduct() {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    const day = currentDate.getDate().toString().padStart(2, '0');
    const hours = currentDate.getHours().toString().padStart(2, '0');
    const minutes = currentDate.getMinutes().toString().padStart(2, '0');
    const seconds = currentDate.getSeconds().toString().padStart(2, '0');

    const currentDateTimeString = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;


    const productNameDropdown = document.getElementById('productName');
    const selectedOption = productNameDropdown.options[productNameDropdown.selectedIndex];
    const selectedValue = selectedOption.value;
    const idCompra = generateRandomIdCompra();
    const productDescription = document.getElementById('productDescription').value;
    const mesPremium = parseInt(document.getElementById('mesPremium').value, 10);
    const idServe = document.getElementById('idServe').value;
    const nameServe = document.getElementById('nameServe').value;
    const idUserServe = document.getElementById('idUserServe').value;
    const nameUserDiscord = document.getElementById('nameUserDiscord').value;
    const priority = parseInt(document.getElementById('priority').value, 10);
    const priorityDropdown = document.getElementById('priority');
    const selectedOptionpriority = priorityDropdown.options[priorityDropdown.selectedIndex];
    const selectedValuprioritye = selectedOptionpriority.value;

    const newProductData = {
        nameProduct: parseInt(selectedValue),
        idCompra: idCompra,
        description: productDescription,
        created: currentDateTimeString,
        mesPremium: mesPremium,
        idServe: idServe,
        nameServe: nameServe,
        idUserServe: idUserServe,
        nameUserDiscord: nameUserDiscord,
        priority: parseInt(selectedValuprioritye)

    };

    console.log(JSON.stringify(newProductData))


    fetch('http://localhost:5018/ProductPost', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newProductData)
    })
        .then(response => {
            if (response.ok) {

                console.log('Produit ajouté avec succès !');


                closePopup();


                fetchData();
            } else {
                console.error('Erreur lors de l\'ajout du produit à l\'API.');
            }
        })
        .catch(error => {
            console.error('Erreur lors de l\'ajout du produit à l\'API :', error);
        });
}

function openSearch() {
    const searchPopup = document.getElementById('searchPopup');
    if (searchPopup) {
        searchPopup.style.display = 'block';
    }
}

function closeSearch() {
    const searchPopup = document.getElementById('searchPopup');
    searchPopup.style.display = 'none';
}




function openSearchResultPopup(searchData) {
    const searchResultPopup = document.getElementById('searchResultPopup');
    const searchResultContent = document.getElementById('searchResultContent');

    // Efface le contenu précédent du popup
    searchResultContent.innerHTML = '';

    // Remplissez le popup avec les valeurs de la recherche
    for (const key in searchData) {
        if (searchData.hasOwnProperty(key)) {
            const value = searchData[key];
            const resultItem = document.createElement('p');
            resultItem.textContent = `${key}: ${value}`;
            searchResultContent.appendChild(resultItem);
        }
    }

    // Affichez le popup
    searchResultPopup.style.display = 'block';
}

function closeSearchResultPopup() {
    const searchResultPopup = document.getElementById('searchResultPopup');
    searchResultPopup.style.display = 'none';
}

function searchProduct() {
    const searchIdCompra = document.getElementById('searchIdCompra').value;


    fetch(`http://localhost:5018/Compra/${searchIdCompra}`, {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {

            openSearchResultPopup(data);
        })
        .catch(error => {
            console.error('Erreur lors de la recherche du produit :', error);
        });
}

