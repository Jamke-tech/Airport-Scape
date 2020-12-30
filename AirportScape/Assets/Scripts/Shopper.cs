using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Shopper : MonoBehaviour
{
    private BoxCollider2D boxCollider;
    private Rigidbody2D rb2D;
    private string[] directions = { "right", "up", "left", "down" };
    private float tiempo;
    RaycastHit2D objectHit;
    private int pos;
    private  int horizontal;
    private int vertical;
    public LayerMask blockinLayer;
    private SpriteRenderer spriteR;
    public Sprite[] sprites;


    // Start is called before the first frame update
    void Start()
    {
        spriteR = gameObject.GetComponent<SpriteRenderer>();
        tiempo = 3;
        pos = 0;
        boxCollider = GetComponent<BoxCollider2D>();
        rb2D = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {
        tiempo = tiempo - 1 * Time.deltaTime;
        if (tiempo >= 0)
        {
            string direction = directions[pos];
            spriteR.sprite = sprites[pos];

            if (direction == "right")
            {
                horizontal = 1;
                vertical = 0;

            }
            else if (direction=="up")
            {
                horizontal = 0;
                vertical = 1;
                
            }
            else if (direction == "left")
            {
                horizontal = -1;
                vertical = 0;
            }
            else 
            {
                horizontal = 0;
                vertical = -1;
            }

            objectHit = Watch(10 * horizontal, 10 * vertical);

            if (objectHit != false)
            {
                if (objectHit.collider.tag == "Player")
                {
                    // hem de posar el que fa el shopper quan veu el jugador.
                    Debug.Log("Player Seen");
                }
            }


        }
        else
        {
            if (pos < directions.Length-1)
            {
                pos++;
            }
            else
            {
                pos = 0;
            }
                     
            
            
            tiempo = 3;
            //Cambiamos el sprite
        }

    }
    public RaycastHit2D Watch(int xCells, int yCells)
    {
        Vector2 start = transform.position;
        Vector2 end = start + new Vector2(xCells,yCells);
        boxCollider.enabled = false;
        RaycastHit2D hit = Physics2D.Linecast(start, end, blockinLayer);
        boxCollider.enabled = true;
        return hit;
    }
}
