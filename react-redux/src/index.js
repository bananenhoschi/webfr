import React from 'react'
import thunk from 'redux-thunk'
import _ from 'lodash'
import {applyMiddleware, createStore} from 'redux'
import {Provider} from 'react-redux'
import ReactDOM from 'react-dom'
import App from './App'

// TODO: Reducer um die beiden neuen Action-Typen (LOADING_MOVIES, MOVIES_LOADED) erweitern.
const ACTIONS = {
    'UPDATE_FILTER_TERM': (state, action) => ({...state, filterTerm: action.filterTerm}),
    'LOADING_MOVIES': (state, action) => ({...state, isLoading: true}),
    'MOVIES_LOADED': (state, action) => ({...state, movies: action.movies, isLoading: false})
}

const reducer = (state, action) => _.get(ACTIONS, action.type, _.identity)(state, action)

// TODO: InitialState anpassen. Neu enthält er eine leere Movie-Liste, filterTerm und isLoading auf den Defaults.
const initialState = {
    movies: [],
    filterTerm: '',
    isLoading: false
}

// TODO: applyMiddleware(ReduxThunk) hinzufügen. Siehe auch AB Redux.
const store = createStore(reducer, initialState, applyMiddleware(thunk))

ReactDOM.render(<Provider store={store}><App/></Provider>, document.getElementById('app'))
