const redux = require('redux')
const logFactory = require('redux-logger')
const thunk = require('redux-thunk').default

const logger = logFactory.createLogger({
    colors: false
});

function counterReducer(state = 0, action) {
    switch (action.type) {
        case 'INCREMENT':
            return state.counter + 1
        case 'DECREMENT':
            return state.counter - 1
        default:
            return state
    }
}


function waitingReducer(state = false, action) {
    switch (action.type) {
        case 'WAITING':
            return true
        case 'RUNNING':
            return false
        default:
            return state
    }
}

function incrementAsync() {
    return (dispatch) => {
        dispatch({type: 'WAITING'})
        setTimeout(() => {
            dispatch({type: 'RUNNING'})
            dispatch({type: 'INCREMENT'})
        }, 2000);
    }
}

const reducers = redux.combineReducers({ counter: counterReducer,
    waiting: waitingReducer
})

let store = redux.createStore(reducers, redux.applyMiddleware(thunk, logger));

store.subscribe(() => console.log("The actual state counter is " + store.getState()))

store.dispatch({type: 'INCREMENT'})
store.dispatch(incrementAsync())
store.dispatch({type: 'INCREMENT'})
store.dispatch({type: 'DECREMENT'})
