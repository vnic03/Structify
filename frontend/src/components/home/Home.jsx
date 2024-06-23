import React from "react";
import { Link } from "react-router-dom";
import './home.css';


function Home() {
    return (
        <div className="Home">
            <header className="Home-header">
                <h1>DataStructures and Algorithms</h1>
            </header>
            <main>
                <div className="cards-container">
                    <Link to="/stack">
                        <StructureCard name="Stack" image="/assets/stack.png"/>
                    </Link>
                    <Link to="/queue">
                        <StructureCard name="Queue" image="/assets/queue.jpeg"/>
                    </Link>
                </div>
            </main>
        </div>
    );
}


function StructureCard({name, image}) {
    return (
        <div className="card">
            <img src={image} alt={`${name} Icon`}/>
            <h2>{name}</h2>
        </div>
    );
}

export default Home;