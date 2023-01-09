import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayBooks();
});

/**
 * Fetches all libraries and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayBooks() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayLibraries(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/books', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display libraries.
 *
 * @param {{books: string[]}} books
 */
function displayLibraries(books) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    books.books.forEach(book => {
        tableBody.appendChild(createTableRow(book));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, title: string}} book
 * @returns {HTMLTableRowElement}
 */
function createTableRow(book) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(book.title));
    //tr.appendChild(createLinkCell('view', '../library_view/library_view.html?library=' + library.libraryId));
    tr.appendChild(createLinkCell('edit', '../library_edit/library_edit.html?library=' + book.id));
    tr.appendChild(createButtonCell('delete', () => deleteBook(book.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number } book to be deleted
 */
function deleteBook(book) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayBooks();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/books/' + book, true);
    xhttp.send();
}
