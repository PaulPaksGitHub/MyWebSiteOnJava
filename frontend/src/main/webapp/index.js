import React from './js/react';
import { render } from './js/react-dom';

function Hello () {
    return (
        <div>
            <h2>hello</h2>
        </div>
    )
}

render(<Hello />, document.getElementById('root'));
