using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Thief : MonoBehaviour
{
    public int speed;
    private Rigidbody2D rb2D;
    private Transform target;
    private Vector3 Towards;
    
    // Start is called before the first frame update
    void Start()
    {
        target = GameObject.FindGameObjectWithTag("Player").GetComponent<Transform>();
        rb2D = GetComponent<Rigidbody2D>();
        speed = 5;
    }

    // Update is called once per frame
    void Update()
    {
        Towards = Mathf.MoveTowards(transform.position, target.position, speed * Time.deltaTime);      
        rb2D.MovePosition(Towards); 

        //.MovePosition(GetComponent<Transform>().position + inputplayer * velocidad * Time.deltaTime);
    }
}
